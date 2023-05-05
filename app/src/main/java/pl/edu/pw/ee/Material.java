package pl.edu.pw.ee;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Material {
    
    public static final Material SHINY_MATERIAL = new Material(0.8, 0.2, 0.2, 50);
    public static final Material SEMI_SHINY_MATERIAL = new Material(0.7, 0.4, 0.3, 25);
    public static final Material SEMI_MATTE_MATERIAL = new Material(0.4,0.6,0.4, 10);
    public static final Material MATTE_MATERIAL = new Material(0.1,0.8,0.5, 1);

    private final double specularReflectionConst;
    private final double diffuseReflectionConst;
    private final double ambientReflectionConst;
    private final double shininess;

    public double k_s() {
        return specularReflectionConst;
    }

    public double k_d() {
        return diffuseReflectionConst;
    }

    public double k_a() {
        return ambientReflectionConst;
    }

    public double alpha() {
        return shininess;
    }

}
