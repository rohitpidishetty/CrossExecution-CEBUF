import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Base64;
import java.util.Scanner;

public class cmd {

  public static void main(String[] args) throws Exception {
    System.out.println("Enter a message:");
    String input = new String();
    Scanner inp = new Scanner(System.in);
    input = inp.nextLine();
    File file = new File("./cross_exe_mem.cebuf");
    String base64 = Base64.getEncoder().encodeToString(input.getBytes());
    FileWriter writer = new FileWriter(file);
    BufferedWriter write = new BufferedWriter(writer);
    write.write(base64);
    write.close();
    writer.close();

    ProcessBuilder pb = new ProcessBuilder(
      "cmd.exe",
      "/c",
      "python cmd_invoke.py"
    );
    Process p = pb.inheritIO().start();
    int exitCode = p.waitFor();

    System.out.println("Cross-Exe completed - " + exitCode);
    if (exitCode == 0) {
      FileReader read = new FileReader(file);
      BufferedReader reader = new BufferedReader(read);
      Scanner src = new Scanner(reader);
      String b64_encoded = src.nextLine();
      byte[] decoded = Base64.getDecoder().decode(b64_encoded.getBytes());
      System.out.println(new String(decoded, "UTF-8"));
    }
  }
}
