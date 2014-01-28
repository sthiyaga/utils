#!/usr/bin/python
#
# Script to get the list of Field API Names that are used in the different layouts
# Run the script from Eclipse Project directory for the Salesforce Org
#
# Copyright 2014 Senthil Thiyagarajan
# 
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

from os import listdir
from os.path import isfile, join
import re

layout_dir = "./src/layouts";
layout_extn = ".layout";
tag_name = "<field>";
break_tag_value = "NAME";

def run():
	for f in [ f for f in listdir(layout_dir) if f.endswith(layout_extn) and isfile(join(layout_dir, f)) ]:
		out_file = open(layout_dir + "/" + f + ".txt", "w");

		out_file.write(f + "\n");
		for line in open(layout_dir + "/" + f):
			if tag_name in line:
				if break_tag_value in line:
					break;
				out_file.write(line[line.index(">")+1 : line.index("</")] + "\n");

		out_file.close();

def main():
	run();

if __name__ == "__main__":
	main();

