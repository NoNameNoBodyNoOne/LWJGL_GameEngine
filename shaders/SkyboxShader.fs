#version 400 core

#define CUT_OFF 0.5

//uniform sampler2D sampler;
uniform vec3 light_dir = vec3(0,1,0);
uniform vec4 light_color = vec4(1,1,1,1);
uniform vec4 color = vec4(1,1,1,1);
uniform float ambient = 0.3;
uniform float light_intensity = 1;

in vec3 tex_coords;

void main() {
   // vec4 texture_col = texture2D(sampler, tex_coords);

    vec3 unit_light_dir = normalize(light_dir);
    vec3 unit_frag_normal = normalize(tex_coords);

    float sunSize = 300;

    float spec_factor = max(0, dot(unit_light_dir, unit_frag_normal));
    float damped_factor = pow(spec_factor, sunSize);
    vec3 spec_color = damped_factor * light_intensity * light_color.xyz;

    float dayTime = min(1, dot(vec3(0,1,0), unit_light_dir) + 0.5);
    float dayTimeSky = max(ambient, dayTime);
    float dayTimeSun = max(0, dayTime);

    vec3 colorDir = color.xyz * min(1, max(ambient, dot(vec3(0,1,0), unit_frag_normal) * 0.5 + 1)) * dayTimeSky;

    gl_FragColor = vec4((colorDir + spec_color * dayTimeSun),1);
}