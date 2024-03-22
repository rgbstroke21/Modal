import org.apache.spark.sql.functions._

// Assuming your DataFrame is named 'df' and the column containing the array of structs is named 'arrayOfStructs'
val countNullA = df.withColumn("null_a_count", size(filter($"arrayOfStructs", struct => struct("a").isNull)))

countNullA.show()
