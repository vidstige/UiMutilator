package se.vidstige.android.uimultimator;

import java.io.BufferedReader;
import java.io.IOException;

class DummyParser implements StreamParser {

	@Override
	public void parse(BufferedReader input) throws IOException, UiMultimatorException {
		while (input.readLine() != null) {	}
	}
}
