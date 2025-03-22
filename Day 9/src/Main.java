import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        String input = sc.nextLine();
        ArrayList<Integer> diskMap = new ArrayList<>();
        long firstAnswer = 0;
        long secondAnswer;
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j <  input.charAt(i)-'0'; j++) {
                if (i%2 == 0) {
                    diskMap.add(i/2);
                } else {
                    diskMap.add(-1);
                }
            }
        }
        for (int i = 0; i < diskMap.size(); i++) {
            if (diskMap.get(i) == -1) {
                diskMap.set(i, diskMap.getLast());
                diskMap.removeLast();
            }
            while (diskMap.getLast() == -1) {
                diskMap.removeLast();
            }
            firstAnswer+=diskMap.get(i)*i;
        }

        secondAnswer = compactFiles(input);
        System.out.println("First answer: " + firstAnswer);
        System.out.println("Second answer: " + secondAnswer);
    }

    static long compactFiles(String diskMap) {
        List<Integer> disk = parseDiskMap(diskMap);

        Map<Integer, Integer> fileSizes = new HashMap<>();
        int fileId = 0;
        for (int i = 0; i < diskMap.length(); i++) {
            int len = diskMap.charAt(i) - '0';
            if (i % 2 == 0) {
                fileSizes.put(fileId, len);
                fileId++;
            }
        }

        List<Integer> fileIds = new ArrayList<>(fileSizes.keySet());
        fileIds.sort(Collections.reverseOrder());

        for (int id : fileIds) {
            int sizeToMove = fileSizes.get(id);
            int currentStart = findFileStart(disk, id, sizeToMove);
            if (currentStart == -1) continue;

            int freeSpaceStart = -1;
            int freeSpaceCount = 0;
            for (int i = 0; i < currentStart; i++) {
                if (disk.get(i) == -1) {
                    if (freeSpaceStart == -1) freeSpaceStart = i;
                    freeSpaceCount++;
                    if (freeSpaceCount >= sizeToMove) break;
                } else {
                    freeSpaceStart = -1;
                    freeSpaceCount = 0;
                }
            }

            if (freeSpaceCount >= sizeToMove && freeSpaceStart != -1) {
                for (int j = 0; j < sizeToMove; j++) {
                    disk.set(currentStart + j, -1);
                }
                for (int j = 0; j < sizeToMove; j++) {
                    disk.set(freeSpaceStart + j, id);
                }
            }
        }

        long checksum = 0;
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) != -1) {
                checksum += (long) i * disk.get(i);
            }
        }
        return checksum;
    }

    static List<Integer> parseDiskMap(String diskMap) {
        List<Integer> disk = new ArrayList<>();
        for (int i = 0; i < diskMap.length(); i++) {
            int len = diskMap.charAt(i) - '0';
            if (i % 2 == 0) {
                int fileId = i / 2;
                for (int j = 0; j < len; j++) {
                    disk.add(fileId);
                }
            } else {
                for (int j = 0; j < len; j++) {
                    disk.add(-1);
                }
            }
        }
        return disk;
    }

    static int findFileStart(List<Integer> disk, int fileId, int size) {
        for (int i = 0; i <= disk.size() - size; i++) {
            boolean match = true;
            for (int j = 0; j < size; j++) {
                if (disk.get(i + j) != fileId) {
                    match = false;
                    break;
                }
            }
            if (match) return i;
        }
        return -1;
    }
}