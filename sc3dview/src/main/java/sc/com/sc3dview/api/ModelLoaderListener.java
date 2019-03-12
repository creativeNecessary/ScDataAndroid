package sc.com.sc3dview.api;


import sc.com.sc3dview.model.ModelObject;


public interface ModelLoaderListener {
    void loadedUpdate(float progress);

    void loadedFinish(ModelObject modelObject);

    void loaderCancel();

    void loaderFailure();
}
