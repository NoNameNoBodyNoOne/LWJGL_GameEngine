#version 400 core

in vec3 vertices;

out vec3 tex_coords;

uniform mat4 view_matrix;
uniform mat4 projection_matrix;

void main(){
    gl_Position = projection_matrix * vec4((view_matrix * vec4(vertices,0)).xyz,1);
    tex_coords = vertices;
}