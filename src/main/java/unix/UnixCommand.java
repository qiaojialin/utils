package unix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UnixCommand {


    /**
     * wget url to dir

     return code explain:

     0       No problems occurred
     1       Generic error code
     2       Parse error — for instance, when parsing command-line options, the .wgetrc or .netrc…
     3       File I/O error
     4       Network failure
     5       SSL verification failure
     6       Username/password authentication failure
     7       Protocol errors
     8       Server issued an error response

     *
     * @param url file url
     * @param dir where file be saved, including file name e.g.(sqldb/configdata.file)
     * @return status code
     * @throws IOException
     * @throws InterruptedException
     */
    public static int wget(String url, String dir) throws IOException, InterruptedException {
        String wget = String.format("wget -O %s %s", dir, url);
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(wget);
        InputStream stderr = proc.getErrorStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null)
            System.out.println(line);
        int exitCode = proc.waitFor();
        System.out.println("wget exit code: " + exitCode);
        return exitCode;
    }
}
