package co.uk.ordnancesurvey.testutils;

import java.io.File;
import javax.activation.MimetypesFileTypeMap;


    public class Readfilesfromfolder{


        private String dirName = "";
        private String fileMimeType = "";

        private File folder = new File("");
        private String temp = "";



        public void getTheDirectory(String dir)
        {
            dirName = dir;
        }

        public void getFileMimeType(String mime)
        {
            fileMimeType = mime;
        }


        static ReadFilesFromFolder rff = new ReadFilesFromFolder();


        public static void main(String[] args)
        {
            // TODO Auto-generated method stub
            rff.listFilesForFolder();
        }

        public  void listFilesForFolder()
        {

            if(dirName.equals(""))
            {
                folder = new File("C:\\Ram\\Data");
            }
            else
            {
                folder = new File(dirName);
            }

            if(fileMimeType.equals(""))
            {
                fileMimeType = "csv";
            }

            System.out.println("Reading files under the folder "+ folder.getAbsolutePath());


            for (final File fileEntry : folder.listFiles())
            {
                if (fileEntry.isDirectory())
                {
                    // System.out.println("Reading files under the folder "+folder.getAbsolutePath());
                    dirName = fileEntry.getAbsolutePath();
                    //listFilesForFolder(fileEntry);
                    listFilesForFolder();
                }
                else
                {
                    if (fileEntry.isFile())
                    {
                        temp = fileEntry.getName();
                        if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals(fileMimeType))
                        {
                            // System.out.println("File= " + folder.getAbsolutePath()+ "\\" + fileEntry.getName());
                            System.out.println("File= " + folder.getAbsolutePath()+ "\\" + fileEntry.getName());

                            System.out.println("Size: "  + "  " + fileEntry.length());

                            System.out.println("Mime Type: " + new MimetypesFileTypeMap().getContentType(fileEntry));

                            System.out.println("Extn : " + temp.substring(temp.lastIndexOf('.') + 1, temp.length()));

                        }
                    }

                }
            }
        }

        public String getFileMimeType() {
            return fileMimeType;
        }

        public void setFileMimeType(String fileMimeType) {
            this.fileMimeType = fileMimeType;
        }

        private static class ReadFilesFromFolder {
            public void listFilesForFolder() {
            }
        }
    }

