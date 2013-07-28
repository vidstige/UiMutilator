package se.vidstige.android.uimultimator;

import java.io.BufferedReader;

interface StreamParser {
	void parse(BufferedReader input) throws Exception;
	void parseErrors(BufferedReader input) throws Exception;
}
