#version 400 core

in vec3 vertices;
in vec2 textures;
in vec3 normals;

out vec2 tex_coords;
out vec3 frag_normal;
out vec3 camera_dir;

uniform mat4 transform_matrix;
uniform mat4 view_matrix;
uniform mat4 projection_matrix;

void main(){
    tex_coords = textures;
    frag_normal = normals;
    vec4 worldPos = transform_matrix * vec4(vertices,1);
    gl_Position = projection_matrix * view_matrix * worldPos;

    camera_dir = (inverse(view_matrix) * vec4(0,0,0,1)).xyz - worldPos.xyz;
}