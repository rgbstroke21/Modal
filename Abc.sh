#!/bin/bash

# Define HDFS paths
input_path="hdfs://your_input_path.csv"
output_path="hdfs://your_output_path.csv"

# Define columns to keep
selected_columns="1,2,3"  # Adjust column indices as needed

# Use awk to filter columns
hadoop fs -cat $input_path | awk -v cols="$selected_columns" 'BEGIN {FS=OFS=","} {split(cols, arr, ","); for (i in arr) printf $arr[i] (i==length(arr) ? ORS : OFS)}' | hadoop fs -put - $output_path
