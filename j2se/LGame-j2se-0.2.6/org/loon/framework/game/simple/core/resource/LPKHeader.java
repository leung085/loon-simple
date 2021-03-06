package org.loon.framework.game.simple.core.resource;

import org.loon.framework.game.simple.utils.NumberUtils;

/**
 * Copyright 2008 - 2009
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * @project loonframework
 * @author chenpeng
 * @email：ceponline@yahoo.com.cn
 * @version 0.1
 */
public class LPKHeader {

	private int identity;

	private byte[] password;

	private float version = 1.0F;

	private long tables = 0;

	public LPKHeader() {
		this.password = new byte[LPKType.LF_PASSWORD_LENGTH];
	}

	public LPKHeader(byte[] password, float version, long tables) {
		this.password = new byte[LPKType.LF_PASSWORD_LENGTH];
		for (int i = 0; i < LPKType.LF_PASSWORD_LENGTH; this.password[i] = password[i], i++)
			;
		this.version = version;
		this.tables = tables;
	}

	public long getTables() {
		return tables;
	}

	public void setTables(long tables) {
		this.tables = tables;
	}

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) {
		this.version = version;
	}

	public int getPAKIdentity() {
		return LPKType.LF_PAK_ID;
	}

	public void setPAKIdentity(int id) {
		this.identity = id;
	}

	public boolean validatePAK() {
		return identity == LPKType.LF_PAK_ID;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(long pass) {
		this.password = NumberUtils.addZeros(pass, LPKType.LF_PASSWORD_LENGTH)
				.getBytes();
	}

	public void setPassword(byte[] password) {
		for (int i = 0; i < LPKType.LF_PASSWORD_LENGTH; password[i] = password[i], i++)
			;
	}

	public static int size() {
		return 4 + LPKType.LF_PASSWORD_LENGTH + 4 + 4 + 1;
	}

}
