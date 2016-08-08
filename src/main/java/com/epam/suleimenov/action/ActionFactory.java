package com.epam.suleimenov.action;

import com.epam.suleimenov.util.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ActionFactory {

    private Map<String, Action> actions;
    private Properties properties;
    private final String ACTION_PROPERTY = "actions.properties";

    public ActionFactory() {
        actions = new HashMap<>();
        properties = Utils.getFile(ACTION_PROPERTY);

        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            actions.put(key, getActionClassByName(value));
        }
    }

    public Action getAction(String actionName) {
        return actions.get(actionName);
    }

    public Action getActionClassByName(String actionName) {
        Action action = null;
        String path = ActionFactory.class.getPackage().getName() + "." + actionName;
        try {
            action = (Action) Class.forName(path).newInstance();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (InstantiationException instantiationException) {
            instantiationException.printStackTrace();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
        return action;
    }
}
