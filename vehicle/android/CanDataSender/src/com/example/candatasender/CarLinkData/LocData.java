/*
 * Copyright 2015 Autoware Foundation. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.candatasender.CarLinkData;

import java.io.Serializable;

public class LocData implements Serializable {
	private static final long serialVersionUID = -2652898043418591821L;
	public static final String name = "CarLinkLocData";
	public long    timestamp;
	public double  lat;
	public double  lon;

	public static LocData parse(String line) throws NumberFormatException {
		LocData loc = new LocData();

		String[] data = line.split(",");
		if (data.length != 4) throw new NumberFormatException(name + " CSV format error: column = " + data.length);

		// TimeStamp[ms],Lat,Lon
		loc.timestamp = Long.parseLong(data[0].trim());
		loc.lat       = Double.parseDouble(data[2].trim());
		loc.lon       = Double.parseDouble(data[3].trim());

		return loc;
	}
}
