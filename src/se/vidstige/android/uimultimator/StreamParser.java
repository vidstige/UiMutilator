package se.vidstige.android.uimultimator;

import java.io.BufferedReader;
import java.io.IOException;

interface StreamParser {
	void parse(BufferedReader input) throws IOException, UiMultimatorException;
}
