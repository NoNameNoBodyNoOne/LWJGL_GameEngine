package gameEngine.renderEngine;

import gameEngine.renderEngine.Meshes.Mesh;
import gameEngine.renderEngine.Meshes.MeshData;
import gameEngine.renderEngine.Meshes.MeshVNTI;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.ArrayList;

public class OBJLoader {

    private static void loadDataFromObj(String filepath, MeshData.VNTI meshData){
        ArrayList<Float> vertList = new ArrayList<Float>();
        ArrayList<Float> uvsList = new ArrayList<Float>();
        ArrayList<Float> normList = new ArrayList<Float>();
        ArrayList<Integer> indicesList = new ArrayList<Integer>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File(filepath)));
            String line;
            while((line = reader.readLine()) != null && !(line.startsWith("f "))){
                String[] currentLine = line.split(" ");

                if(line.startsWith("v ")){
                    vertList.add(Float.parseFloat(currentLine[1]));
                    vertList.add(Float.parseFloat(currentLine[2]));
                    vertList.add(Float.parseFloat(currentLine[3]));
                }else if(line.startsWith("vt ")){
                    uvsList.add(Float.parseFloat(currentLine[1]));
                    uvsList.add(Float.parseFloat(currentLine[2]));
                }else if(line.startsWith("vn ")) {
                    normList.add(Float.parseFloat(currentLine[1]));
                    normList.add(Float.parseFloat(currentLine[2]));
                    normList.add(Float.parseFloat(currentLine[3]));
                }
            }
            meshData.vertices = ArrayUtils.toPrimitive(vertList.toArray(new Float[0]),0f);
            meshData.uvs = new float[(vertList.size()/3)*2];
            meshData.normals = new float[vertList.size()];
            do{
                if(line.startsWith("f ")){
                    String[] currentLine = line.split(" ");
                    String[] vert1 = currentLine[1].split("/");
                    String[] vert2 = currentLine[2].split("/");
                    String[] vert3 = currentLine[3].split("/");

                    processVertex(vert1, meshData, uvsList, normList);
                    processVertex(vert2, meshData, uvsList, normList);
                    processVertex(vert3, meshData, uvsList, normList);

                    indicesList.add(Integer.parseInt(vert1[0])-1);
                    indicesList.add(Integer.parseInt(vert2[0])-1);
                    indicesList.add(Integer.parseInt(vert3[0])-1);
                }
            }while((line = reader.readLine()) != null);
            meshData.indices = ArrayUtils.toPrimitive(indicesList.toArray(new Integer[0]),0);
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void processVertex(String[] vertexData, MeshData.VNTI meshData, ArrayList<Float> uvsList, ArrayList<Float> normalsList){
        int vertIndex = Integer.parseInt(vertexData[0])-1;
        int uvIndex = Integer.parseInt(vertexData[1])-1;
        int normalIndex = Integer.parseInt(vertexData[2])-1;
        meshData.uvs[vertIndex*2] = uvsList.get(uvIndex*2);
        meshData.uvs[vertIndex*2+1] = uvsList.get(uvIndex*2+1);

        meshData.normals[vertIndex*3] = normalsList.get(normalIndex*3);
        meshData.normals[vertIndex*3+1] = normalsList.get(normalIndex*3+1);
        meshData.normals[vertIndex*3+2] = normalsList.get(normalIndex*3+2);
    }

    public static Mesh LoadToMesh(String filepath){
        MeshData.VNTI meshData = new MeshData.VNTI();

        loadDataFromObj(filepath,meshData);
        return new MeshVNTI(meshData.vertices, meshData.uvs, meshData.normals, meshData.indices);
    }

    public static void WriteOBJToMeshFile(String sourcePath, String destPath){
        MeshData.VNTI meshData = new MeshData.VNTI();
        loadDataFromObj(sourcePath,meshData);
        try {
            FileOutputStream fout = new FileOutputStream(destPath);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(meshData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Mesh LoadMeshFromMeshFile(String filepath){
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            MeshData.VNTI meshData = (MeshData.VNTI) objectIn.readObject();
            objectIn.close();
            return new MeshVNTI(meshData.vertices, meshData.uvs, meshData.normals, meshData.indices);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
