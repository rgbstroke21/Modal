#!/bin/bash

#!/bin/bash

# Define HDFS paths
input_path="hdfs://your_input_path.csv.gz"
output_path="hdfs://your_output_path.csv"

# Use zcat to decompress and copy to a new CSV file in HDFS
zcat <(hadoop fs -cat $input_path) | hadoop fs -put - $output_path




# Define HDFS paths
input_path="hdfs://your_input_path.csv"
output_path="hdfs://your_output_path.csv"

# Define columns to keep
selected_columns="1,2,3"  # Adjust column indices as needed

# Use awk to filter columns
hadoop fs -cat $input_path | awk -v cols="$selected_columns" 'BEGIN {FS=OFS=","} {split(cols, arr, ","); for (i in arr) printf $arr[i] (i==length(arr) ? ORS : OFS)}' | hadoop fs -put - $output_path
