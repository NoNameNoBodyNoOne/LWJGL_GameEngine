package gameEngine;

import com.google.gwt.i18n.client.HasDirection;
import gameEngine.renderEngine.Camera;
import gameEngine.renderEngine.DirectionalLight;

public interface MonoBehaviour {
    Camera MainCamera = EngineManager.MainCamera;

    void Start();
    void Update();
}
