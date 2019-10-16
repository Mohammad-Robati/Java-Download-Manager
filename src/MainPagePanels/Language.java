package MainPagePanels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Language {

    /**
     * Reads the language files and save them in an arraylist
     * for the UI
     */

    private static ArrayList<String> english;
    private static ArrayList<String> farsi;

    public enum ENGLISH {
        DOWNLOAD {
            public String toString() {
                return Language.english.get(0);
            }
        },
        NEW_DOWNLOAD {
            public String toString() {
                return Language.english.get(1);
            }
        },
        START {
            public String toString() {
                return Language.english.get(2);
            }
        },
        PAUSE {
            public String toString() {
                return Language.english.get(3);
            }
        },
        CANCEL {
            public String toString() {
                return Language.english.get(4);
            }
        },
        REMOVE {
            public String toString() {
                return Language.english.get(5);
            }
        },
        SETTINGS {
            public String toString() {
                return Language.english.get(6);
            }
        },
        EXPORT {
            public String toString() {
                return Language.english.get(7);
            }
        },
        EXIT {
            public String toString() {
                return Language.english.get(8);
            }
        },
        HELP {
            public String toString() {
                return Language.english.get(9);
            }
        },
        LANGUAGE {
            public String toString() {
                return Language.english.get(10);
            }
        },
        ENGLISH {
            public String toString() {
                return Language.english.get(11);
            }
        },
        FARSI {
            public String toString() {
                return Language.english.get(12);
            }
        },
        DETAILS {
            public String toString() {
                return Language.english.get(13);
            }
        },
        PROCESSING {
            public String toString() {
                return Language.english.get(14);
            }
        },
        COMPLETED {
            public String toString() {
                return Language.english.get(15);
            }
        },
        QUEUES {
            public String toString() {
                return Language.english.get(16);
            }
        }
    }

    public enum FARSI {
        DOWNLOAD {
            public String toString() {
                return Language.farsi.get(0);
            }
        },
        NEW_DOWNLOAD {
            public String toString() {
                return Language.farsi.get(1);
            }
        },
        START {
            public String toString() {
                return Language.farsi.get(2);
            }
        },
        PAUSE {
            public String toString() {
                return Language.farsi.get(3);
            }
        },
        CANCEL {
            public String toString() {
                return Language.farsi.get(4);
            }
        },
        REMOVE {
            public String toString() {
                return Language.farsi.get(5);
            }
        },
        SETTINGS {
            public String toString() {
                return Language.farsi.get(6);
            }
        },
        EXPORT {
            public String toString() {
                return Language.farsi.get(7);
            }
        },
        EXIT {
            public String toString() {
                return Language.farsi.get(8);
            }
        },
        HELP {
            public String toString() {
                return Language.farsi.get(9);
            }
        },
        LANGUAGE {
            public String toString() {
                return Language.farsi.get(10);
            }
        },
        ENGLISH {
            public String toString() {
                return Language.farsi.get(11);
            }
        },
        FARSI {
            public String toString() {
                return Language.farsi.get(12);
            }
        },
        DETAILS {
            public String toString() {
                return Language.farsi.get(13);
            }
        },
        PROCESSING {
            public String toString() {
                return Language.farsi.get(14);
            }
        },
        COMPLETED {
            public String toString() {
                return Language.farsi.get(15);
            }
        },
        QUEUES {
            public String toString() {
                return Language.farsi.get(16);
            }
        }
    }

    public Language() throws IOException {
        english = new ArrayList<>();
        farsi = new ArrayList<>();
        FileReader fileReader1 = new FileReader("src/Language/English.txt");
        FileReader fileReader2 = new FileReader("src/Language/Farsi.txt");
        BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
        BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
        String line;
        while ((line = bufferedReader1.readLine()) != null) {
            english.add(line);
        }
        while ((line = bufferedReader2.readLine()) != null) {
            farsi.add(line);
        }
    }
}
