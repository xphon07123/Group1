package Metrics;

public class Lines {

    public int lineCount(String fileContent) {

        int lines = 0;
        String [] lineKeeper;

        while(fileContent != null) {
            lineKeeper = fileContent.split(("\\r?\\n"));
            lines = lineKeeper.length;
        }
        return lines;
    }

}
