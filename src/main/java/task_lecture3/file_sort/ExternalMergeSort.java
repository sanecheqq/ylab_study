package task_lecture3.file_sort;

import java.io.*;
import java.util.*;

public class ExternalMergeSort {
    public File startSort(File inputFile) throws IOException {
        File outputFile = new File("src/test/java/test_lecture3/file_sort/output.txt");
        List<File> list = sortInChunks(inputFile) ;
        mergeSortedFiles(list, outputFile);
        return outputFile;
    }

    private List<File> sortInChunks(File file) throws IOException {
        List<File> files = new ArrayList<>();
        try (BufferedReader buffReader = new BufferedReader(new FileReader(file))) {
            long blockSizeBytes = estimateBestSizeOfBlocks(file);
            List<Long> listForBlock = new ArrayList<>();
            String line = "";
            try {
                while (line != null) {
                    long curBlockSizeBytes = 0;
                    while ((curBlockSizeBytes < blockSizeBytes) && ((line = buffReader.readLine()) != null)) {
                        listForBlock.add(Long.parseLong(line));
                        curBlockSizeBytes += line.length();
                    }
                    files.add(sortAndSave(listForBlock));
                    listForBlock.clear();
                }
            } catch (EOFException oef) {
                if (listForBlock.size() > 0) {
                    files.add(sortAndSave(listForBlock));
                    listForBlock.clear();
                }
            }
        }
        return files;
    }

    private long estimateBestSizeOfBlocks(File fileToSort) {
        long fileSize = fileToSort.length();
        final int MAX_TEMP_FILES = 1024;
        long blockSize = fileSize / MAX_TEMP_FILES;
        long halfOfFreeMemory = Runtime.getRuntime().freeMemory() / 2;
        if(blockSize < halfOfFreeMemory/2) {
            blockSize = halfOfFreeMemory/2;
        } else if (blockSize >= halfOfFreeMemory) {
            System.err.println("Expect to run out of memory");
        }
        return blockSize;
    }

    private File sortAndSave(List<Long> listForBlock) throws IOException  {
        Collections.sort(listForBlock);
        File newTmpFile = File.createTempFile("chunk" + listForBlock.size(),  "tmp");
        newTmpFile.deleteOnExit();
        try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter(newTmpFile))) {
            for (Long num : listForBlock) {
                buffWriter.write(Long.toString(num));
                buffWriter.newLine();
            }
        }
        return newTmpFile;
    }

    private void mergeSortedFiles(List<File> files, File outputfile) throws IOException {
        PriorityQueue<FileBuffer> fileBuffersQueue = new PriorityQueue<>(11,
                Comparator.comparingLong(fileBuffer -> Long.parseLong(fileBuffer.peek())));
        for (File file : files) {
            FileBuffer fileBuffer = new FileBuffer(file);
            fileBuffersQueue.add(fileBuffer);
        }
        try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter(outputfile))) {
            processingQueueAndMergingChunks(fileBuffersQueue, buffWriter);
        } finally {
            for (FileBuffer fileBuffer : fileBuffersQueue) {
                fileBuffer.close();
            }
        }
    }

    private void processingQueueAndMergingChunks(PriorityQueue<FileBuffer> fileBuffersQueue, BufferedWriter buffWriter) throws IOException {
        while (fileBuffersQueue.size() > 0) {
            FileBuffer fileBuffer = fileBuffersQueue.poll();
            String str = fileBuffer.pop();
            buffWriter.write(str);
            buffWriter.newLine();
            if (fileBuffer.empty()) {
                fileBuffer.buffReader.close();
                fileBuffer.originalfile.delete();
            } else {
                fileBuffersQueue.add(fileBuffer);
            }
        }
    }
}