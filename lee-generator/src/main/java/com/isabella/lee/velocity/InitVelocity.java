package com.isabella.lee.velocity;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class InitVelocity {

    private static VelocityEngine velocityEngine = null;

    private void InitVelocity() {
    }

    private static InitVelocity initVelocity = new InitVelocity();

    public static InitVelocity init() {
        if (initVelocity == null) {
            initVelocity = new InitVelocity();
        }
        if (velocityEngine == null) {
            velocityEngine = new VelocityEngine();
            velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            velocityEngine.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
            velocityEngine.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            velocityEngine.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
            velocityEngine.init();
        }
        return initVelocity;
    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }
}
