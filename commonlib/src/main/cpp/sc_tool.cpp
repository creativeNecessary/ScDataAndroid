//
// Created by Eric on 2018/4/13.
//

#include "sc_tool.h"


jstring getBaseHttpUrl(JNIEnv *env, jclass clazz, jstring type) {
    jboolean isCopy = (jboolean) true;
    const char *char_type = env->GetStringUTFChars(type, &isCopy);
    char *str = (char *) "www.baidu.com";
    if (strcmp(char_type, TYPE_ROBERT) == 0) {
        str = (char *) "https://robertsspaceindustries.com";
    } else if (strcmp(char_type, TYPE_SERVICE_API) == 0) {
        str = (char *) "http://47.97.161.251/api/";
    }
    return env->NewStringUTF(str);

}

jstring getDigitalSignature(JNIEnv *env, jclass clazz, jstring operate, jstring timestamp) {
    const char hexcode[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                            'e', 'f'};
    char *result_char = NULL;

    jstring str_input = getNeedSignatureStr(env,
                                            (char *) env->GetStringUTFChars(operate, NULL),
                                            (char *) env->GetStringUTFChars(timestamp, NULL));

    jclass stringClass = env->FindClass("java/lang/String");

    jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "()[B");

    jbyteArray inputArray = (jbyteArray) env->CallObjectMethod(str_input, getBytes);


    jclass messageDigest_clazz = env->FindClass("java/security/MessageDigest");
    if (messageDigest_clazz == NULL) {
        return NULL;
    }
    jmethodID getInstance = env->GetStaticMethodID(messageDigest_clazz, "getInstance",
                                                   "(Ljava/lang/String;)Ljava/security/MessageDigest;");

    if (getInstance == NULL) {
        return NULL;
    }
    jstring sha1String = env->NewStringUTF("SHA1");
    jobject messageDigest = env->CallStaticObjectMethod(messageDigest_clazz, getInstance,
                                                        sha1String);
    if (messageDigest == NULL) {
        return NULL;
    }
    jmethodID digest = env->GetMethodID(messageDigest_clazz, "digest", "([B)[B");

    jbyteArray resultArray = (jbyteArray) env->CallObjectMethod(messageDigest, digest,
                                                                inputArray);
    if (resultArray == NULL) {
        return NULL;
    }
    env->DeleteLocalRef(messageDigest_clazz);

    jsize array_size = env->GetArrayLength(resultArray);

    jbyte *sha = env->GetByteArrayElements(resultArray, JNI_FALSE);

    result_char = new char[array_size * 2 + 1];
    for (int i = 0; i < array_size; ++i) {
        result_char[2 * i] = hexcode[((unsigned char) sha[i]) / 16];
        result_char[2 * i + 1] = hexcode[((unsigned char) sha[i]) % 16];
    }
    result_char[array_size * 2] = '\0';
    env->ReleaseByteArrayElements(resultArray,sha,0);

    return env->NewStringUTF(result_char);
}


static JNINativeMethod jniMethods[] = {
        {"getSysUrl",           "(Ljava/lang/String;)Ljava/lang/String;",                   (void *) getBaseHttpUrl},
        {"getDigitalSignature", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", (void *) getDigitalSignature}
};


JNIEXPORT int JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *jniEnv;
    if (vm->GetEnv((void **) &jniEnv, JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }

    jclass javaClass = jniEnv->FindClass("sc/com/commonlib/common/util/HttpUtil");
    if (javaClass == NULL) {
        return JNI_ERR;
    }
    //注册
    if (jniEnv->RegisterNatives(javaClass, jniMethods, LENGTH(jniMethods))) {

        return JNI_ERR;

    }

    return JNI_VERSION_1_6;
}

jstring getNeedSignatureStr(JNIEnv *env, char *operate, char *timestamp) {

    size_t length_operate = strlen(operate);
    size_t length_timestamp = strlen(timestamp);
    size_t cycle_index = 0;
    if (length_operate > length_timestamp) {
        cycle_index = length_timestamp * 2;
    } else {
        cycle_index = length_operate * 2;
    }
    char array_operate[40] = {0};
    char array_timestamp[40] = {0};

    strncpy(array_operate, operate, strlen(operate));
    strncpy(array_timestamp, timestamp, strlen(timestamp));


    char result_char[80] = {0};
    size_t index_operate = 0;
    size_t index_timestamp = 0;

    if (array_timestamp[length_timestamp-1] % 2 == 0) {
        for (int i = 0; i < cycle_index; ++i) {
            if (i % 2 == 0) {
                result_char[i] = array_operate[index_operate];
                index_operate++;
            } else {
                result_char[i] = array_timestamp[index_timestamp];
                index_timestamp++;
            }

        }
    } else {
        for (int i = 0; i < cycle_index; ++i) {
            if (i % 2 == 0) {
                result_char[i] = array_timestamp[index_timestamp];
                index_timestamp++;
            } else {
                result_char[i] = array_operate[index_operate];
                index_operate++;
            }

        }
    }


    return env->NewStringUTF(result_char);
}






