package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class LhvTestYlesanneApplication {
	public static void main(String[] args) {
		nameChecker("Osama Bin Laden");
	}
	public static void nameChecker(String nameToCheck) {

		String name = nameToCheck;
		String namesFile = "names_file.txt";
		String noiseFile = "noise_file.txt";
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();

		// Laen 'mürasõnad' setti
		HashSet<String> noiseWords = new HashSet<>();

		try (InputStream noiseFileStream = ClassLoader.getSystemClassLoader().getResourceAsStream(noiseFile);
			 BufferedReader reader = new BufferedReader(new InputStreamReader(noiseFileStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				noiseWords.add(line.trim().toLowerCase());
			}
		} catch (IOException e) {
			System.err.println("Error reading noise file: " + e.getMessage());
			return;
		}

		//Võrdlen nime iga kontrollnimega nimefailis
		InputStream names = classloader.getResourceAsStream(namesFile);
		System.out.println(names);
		try (InputStream nameFileStream = ClassLoader.getSystemClassLoader().getResourceAsStream(namesFile);
			 BufferedReader reader = new BufferedReader(new InputStreamReader(nameFileStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.trim().toLowerCase();

				// Splitin iga nime sõnadeks ja eemaldan sealt mürasõnad
				String[] words = line.split("\\s+");
				List<String> wordsList = new ArrayList<>(Arrays.asList(words));
				wordsList.removeAll(noiseWords);

				// Kontrollin, kas nimi sisaldab kõiki ülejäänud sõnu
				boolean isMatch = true;
				for (String word : wordsList) {
					if (!name.toLowerCase().contains(word)) {
						isMatch = false;
						break;
					}
				}

				if (isMatch) {
					System.out.println("Matching name: " + line);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading names file: " + e.getMessage());
			return;
		}
	}
}
