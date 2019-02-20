#version 400 core

#define CUT_OFF 0.5

uniform sampler2D sampler;
uniform float shine_damper = 50;
uniform float glossiness = 0;
uniform vec3 light_dir = vec3(0,1,0);
uniform vec4 light_color = vec4(1,1,1,1);
uniform vec4 color = vec4(1,1,1,1);
//uniform float ambient = 0.3;
uniform float light_intensity = 1;

in vec2 tex_coords;
in vec3 frag_normal;
in vec3 camera_dir;

float lerp(float a, float b, float f){
    return (f*a+(1-f)*b);
}

void main() {
    vec4 texture_col = texture2D(sampler, tex_coords);

    if(texture_col.a < CUT_OFF) {
        discard;
    }

    vec3 unit_light_dir = normalize(light_dir);
    vec3 unit_frag_normal = normalize(frag_normal);
    vec3 unit_camera_dir = normalize(camera_dir);

    float dayTime = dot(vec3(0,1,0), unit_light_dir);
    float dayTimeDiff = min(1, max(0, dayTime + 0.5));
    float dayTimeSpec = min(1, max(0, dayTime));

    float maxAmbient = 0.5;
    float minAmbient = 0.2;
    float ambient = lerp(maxAmbient, minAmbient, dayTimeDiff);

    float nDot = dot(unit_light_dir, unit_frag_normal);
    float brightness = max(nDot * dayTimeDiff, ambient);
    vec4 diffuse = brightness * light_color * light_intensity;

    vec3 reflect_dir = reflect(-unit_light_dir, unit_frag_normal);
    float spec_factor = dot(reflect_dir, unit_camera_dir);
    spec_factor = max(spec_factor, 0);
    float damped_factor = pow(spec_factor, shine_damper);
    vec4 spec_color = damped_factor * light_color * glossiness;

    gl_FragColor = diffuse * texture_col * color  + spec_color * dayTimeSpec;
}