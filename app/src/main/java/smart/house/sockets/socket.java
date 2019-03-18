package smart.house.sockets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;

import java.net.*;
import java.io.*;

import smart.house.R;

public class socket extends AsyncTask<Object, Integer, Bitmap> {
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Socket sock;
    private Context context;
    private AppCompatImageView imageView;
    private Bitmap myBitmap;
    public void llamada() {

        try {
            Socket echoSocket = new Socket("192.168.100.6", 8080);
            //PrintStream out = new PrintStream(echoSocket.getOutputStream());
            //Scanner scanner = new Scanner(System.in);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            System.out.println("Client Message:");
//            fromUser = stdIn.readLine();
            fromUser = "Michael";
            System.out.println("Client: " + fromUser);
            out.println(fromUser);


            fromServer = in.readLine();
            System.out.println("Server: " + fromServer);

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void reciveImage(){
        try {
            System.out.println("Connecting...");
            sock = new Socket("192.168.100.6", 8080);
            System.out.println(sock);
        } catch(Exception e) {
            System.out.println(e);
        }
el examen va a ser ex
        readFile();
    }

    public void readFile(){
        int i;
        String fileName;
        FileOutputStream fileOutputStream;
        try {
            dataInputStream = new DataInputStream(sock.getInputStream());
            dataOutputStream = new DataOutputStream(sock.getOutputStream());
            fileName = dataInputStream.readUTF();
            System.out.println("File Name Received: " + fileName);



//            if(!imgFile.exists())
//                imgFile.createNewFile();

//            fileOutputStream = context.openFileOutput(fileName, context.MODE_PRIVATE);
            fileOutputStream = context.openFileOutput(fileName, context.MODE_PRIVATE);
            byte[] readData = new byte[1024];

            while ((i = dataInputStream.read(readData)) != -1) {
                fileOutputStream.write(readData, 0, i);
//                fileOutputStream.flush();

                System.out.println("part Image has been Received");
            }

            fileOutputStream.close();
            File imgFile = new  File("/data/data/smart.house/files/" + fileName);
            if(imgFile.exists()){

                myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            }
        } catch (IOException ioe) {
            System.out.println(ioe);
            System.out.println("Failed Receiving");
            return;
        } catch (IllegalArgumentException il){
            System.out.println(il);
        }
        Log.i("SOCKET CLASS", "readFile: Image Received--------");





        //int id = context.getResources().getIdentifier("smart.house:raw/" + fileName, null, null);
        //imageView.setImageDrawable(context.getResources().getDrawable(id));
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected Bitmap doInBackground(Object... objects) {
        context = (Context) objects[0];
        imageView = (AppCompatImageView) objects[1];
        reciveImage();
        return myBitmap;
    }
}