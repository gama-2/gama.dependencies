/**
 * Copyright (c) 2010 Scott A. Crosby. <scott@sacrosby.com>
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 * 
 */

package gama.dependencies.osmosis;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class BlockInputStream {
	// TODO: Should be seekable input stream!
	public BlockInputStream(final InputStream input, final BlockReaderAdapter adaptor) {
		this.input = input;
		this.adaptor = adaptor;
	}

	public void process() throws IOException {
		try {
			while (true) {
				FileBlock.process(input, adaptor);
			}
		} catch (final EOFException e) {
			adaptor.complete();
		}
	}

	public void close() throws IOException {
		input.close();
	}

	InputStream input;
	BlockReaderAdapter adaptor;
}
