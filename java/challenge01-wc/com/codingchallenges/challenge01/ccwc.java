package com.codingchallenges.challenge01;

import com.codingchallenges.challenge01.streamprocessors.models.StreamProcessingResult;
import com.codingchallenges.challenge01.models.ProcessingOptions;
import com.codingchallenges.challenge01.streamprocessors.BufferedByteStreamProcessor;
import com.codingchallenges.challenge01.streamprocessors.StreamProcessor;
import com.codingchallenges.challenge01.utils.CommandLineArgumentParser;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

class ccwc {
	private static final char[] VALID_FLAGS = new char[] { 'l', 'w', 'm', 'c' };

	public static void main(String[] args) throws IOException {
		final StreamProcessor streamProcessor = new BufferedByteStreamProcessor();
		final var parseResult = CommandLineArgumentParser.parseArguments(args, VALID_FLAGS);
		final var processingOptions = generateOptionsFromFlags(parseResult.flags());
		final String currentWorkingDirectory = System.getProperty("user.dir");
		final var files = parseResult.otherArguments().stream().map(filename -> {
			boolean isAbsolutePath = filename.charAt(0) == '/';
			return (isAbsolutePath ? Path.of(filename) : Path.of(currentWorkingDirectory, filename)).toFile();
		}).toArray(File[]::new);

		if (files.length == 0) {
			var result = streamProcessor.processStream(System.in, processingOptions, "");
			printOutput(processingOptions, result);
			return;
		}

		int maxThreads = Runtime.getRuntime().availableProcessors() * 2;
		ExecutorService executor = Executors.newFixedThreadPool(maxThreads);
		List<Future<StreamProcessingResult>> futures = new ArrayList<>();

		for (int i=0; i<files.length; i++) {
			String providedFilename = parseResult.otherArguments().get(i);
			final var file = files[i];

			if (file.isDirectory()) {
				printError(providedFilename, "read: Is a directory");
				continue;
			}
			if (!file.exists()) {
				printError(providedFilename, "open: No such file or directory");
				continue;
			}

			// TODO: Check how binary files are handled

			var fileStream = new FileInputStream(file);
			Callable<StreamProcessingResult> task = () -> streamProcessor.processStream(fileStream, processingOptions, providedFilename);
			futures.add(executor.submit(task));
		}

		long totalResults = 0;
		StreamProcessingResult cumulative = new StreamProcessingResult();
		for (var future : futures) {
			try {
				var result = future.get();
				printOutput(processingOptions, result);
				cumulative = new StreamProcessingResult(cumulative, result, "total");
				totalResults += 1;
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		if (totalResults > 1) {
			printOutput(processingOptions, cumulative);
		}

		executor.shutdown();
	}

	private static void printOutput(ProcessingOptions options, StreamProcessingResult result) {
		System.out.printf(" ");
		if (options.showLineCount()) {
			System.out.printf("%7d ", result.lineCount());
		}
		if (options.showWordCount()) {
			System.out.printf("%7d ", result.wordCount());
		}
		if (options.showByteCount()) {
			System.out.printf("%7d ", result.byteCount());
		}
		if (options.showCharacterCount()) {
			System.out.printf("%7d ", result.characterCount());
		}
		System.out.printf("%s\n", result.filename());
	}

	private static void printError(String filename, String errorMessage) {
		System.out.printf("src.main.com.codingchallenges.challenge01.ccwc: %s: %s\n", filename, errorMessage);
	}

	public static ProcessingOptions generateOptionsFromFlags(Map<Character, Integer> flags) {
		if (flags.isEmpty()) {
			return new ProcessingOptions(true, true, true, false);
		}
		boolean lineCount = flags.containsKey('l');
		boolean wordCount = flags.containsKey('w');
		boolean byteCount = false;
		boolean characterCount = false;

		if (flags.containsKey('m') && flags.containsKey('c')) {
			if (flags.get('m') > flags.get('c')) {
				characterCount = true;
			} else {
				byteCount = true;
			}
		} else {
			byteCount = flags.containsKey('c');
			characterCount = flags.containsKey('m');
		}
		return new ProcessingOptions(lineCount, wordCount, byteCount, characterCount);
	}
}
