//
// Created by Eric on 2018/4/13.
//

#ifndef SCDATAVIEW_SC_TOOL_H
#define SCDATAVIEW_SC_TOOL_H
#define LENGTH(x) ((int) (sizeof(x) / sizeof((x)[0])))

#include <jni.h>
#include <string.h>
#include <stdio.h>
#include <stdint.h>

__BEGIN_DECLS
char *TYPE_ROBERT = (char *) "type_robert";
char *TYPE_SERVICE_API = (char *) "type_service_api";
jstring getBaseHttpUrl(JNIEnv *env, jclass clazz, jstring type);
jstring getDigitalSignature(JNIEnv *env, jclass clazz, jstring operate , jstring timestamp);
jstring getNeedSignatureStr(JNIEnv *env ,char * operate , char * timestamp);
JNIEXPORT int JNICALL JNI_OnLoad(JavaVM *vm, void *reserved);
JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *vm, void *reserved);

__END_DECLS

#endif //SCDATAVIEW_SC_TOOL_H
