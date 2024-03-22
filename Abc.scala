import org.apache.spark.sql.functions._

// Assuming your DataFrame is named 'df' and the column containing the array of structs is named 'arrayOfStructs'

// Explode the array column
val explodedDF = df.withColumn("exploded", explode($"arrayOfStructs"))

// Add a column containing 1 for null "a" values and 0 otherwise
val nullACountDF = explodedDF.withColumn("null_a_count", when($"exploded.a".isNull, 1).otherwise(0))

// Group by the original DataFrame and sum the counts to get the total count of null "a" values
val countNullA = nullACountDF.groupBy().agg(sum($"null_a_count").alias("null_a_count"))

countNullA.show()
