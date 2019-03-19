package smart.house.sockets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.AppCompatImageView;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ImageReciever extends AsyncTask <Object, Integer, Bitmap> {

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Socket socket;
    private Context context;
    private AppCompatImageView imageView;
    private final String IMAGE_NAME = "cameraPic.jpg";

    private Bitmap reciveImage(){
        int i;
        Bitmap bitmap = null;

        FileOutputStream fileOutputStream;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            dataOutputStream.write("PHT".getBytes());

            fileOutputStream = context.openFileOutput(IMAGE_NAME, context.MODE_PRIVATE);
            byte[] readData = new byte[1024];

            while ((i = dataInputStream.read(readData)) != -1)
                fileOutputStream.write(readData, 0, i);

            fileOutputStream.close();
            File imgFile = new  File("/data/data/smart.house/files/" + IMAGE_NAME);

            if(imgFile.exists())
                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (IllegalArgumentException il){
            System.out.println(il);
        }

        return bitmap;
    }

    @Override
    protected Bitmap doInBackground(Object[] objects) {
        context = (Context) objects[0];
        imageView = (AppCompatImageView) objects[1];
        socket = SocketConnection.getInstance().getSocket();

        return reciveImage();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageView.setImageBitmap(bitmap);
    }
}
