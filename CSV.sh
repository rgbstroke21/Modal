#!/bin/bash

# Input file (replace with your actual file name)
input_file="your_file.csv.gz"

# Output file (replace with your desired output file name)
output_file="output.csv"

# Specify columns to select (replace with your desired column names or indices)
selected_columns="column1 column3 column5"

# Unzip and select columns using awk
zcat "$input_file" | awk -v cols="$selected_columns" 'BEGIN{OFS=FS=","} {split(cols, arr, " "); for(i in arr) printf "%s%s", $arr[i], (i==length(arr) ? ORS : OFS)}' > "$output_file"

echo "Selected columns written to $output_file"
