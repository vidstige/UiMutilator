package se.vidstige.android.adb;

import java.io.BufferedReader;
import java.io.IOException;

public interface StreamParser {
	void parse(BufferedReader input) throws IOException;
	void parseErrors(BufferedReader input) throws Exception;
}
