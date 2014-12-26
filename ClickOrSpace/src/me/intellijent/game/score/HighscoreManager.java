package me.intellijent.game.score;

import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class HighscoreManager {
	private ArrayList<Score> scores;
	private static final String HIGHSCORE_FILE = "scores.dat";

	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;

	public HighscoreManager() {
		scores = new ArrayList<Score>();
	}

	public ArrayList<Score> getScores() {
		loadScoreFile();
		sort();
		return scores;
	}

	private void sort() {
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(scores, comparator);
	}

	public void addScore(String name, int score) {
		loadScoreFile();
		scores.add(new Score(name, score));
		updateScoreFile();
	}
	
	public void updateScore(String name, int place) {
		loadScoreFile();
		Score score = scores.get(place);
		scores.remove(place);
		scores.add(place, new Score(name, score.getScore()));
	}

	@SuppressWarnings("unchecked")
	public void loadScoreFile() {
		try {
			inputStream = new ObjectInputStream(new FileInputStream(
					HIGHSCORE_FILE));
			scores = (ArrayList<Score>) inputStream.readObject();
		} catch (Exception e) {
			
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
			}
		}
	}

	public void updateScoreFile() {
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(
					HIGHSCORE_FILE));
			outputStream.writeObject(scores);
		} catch (Exception e) {
			
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
			}
		}
	}

	public String getHighscore(int i) {
		String text = "" + i + ((i == 1) ? "st" : ((i == 2) ? "nd" : ((i == 3) ? "rd" : "th"))) + " ---- ";
		if(getScores().size() <= (i - 1)) {
			text += "00000";
			return text;
		}
		if(getScores().get(i - 1) != null) {
			text += new DecimalFormat("00000").format(getScores().get(i - 1).getScore()) + " - " +getScores().get(i - 1).getName();
		}else {
			text += "00000";
		}
		return text;
	}
}