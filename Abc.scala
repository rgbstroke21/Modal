import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

object NullCountFunction {
  // Define a case class to represent the struct
  case class MyStruct(a: String, b: Int)

  // Define the function to count nulls in the 'a' field of each struct array
  def countNulls(arr: Seq[MyStruct]): Int = {
    arr.count(_.a == null)
  }

  def main(args: Array[String]): Unit = {
    // Initialize SparkSession
    val spark = SparkSession.builder()
      .appName("NullCountFunction")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    // Sample data
    val data = Seq(
      (1, Seq(MyStruct("foo", 10), MyStruct(null, 20), MyStruct("bar", 30))),
      (2, Seq(MyStruct("abc", 40), MyStruct(null, 50), MyStruct("def", 60))),
      (3, Seq(MyStruct(null, 70), MyStruct("xyz", 80), MyStruct(null, 90)))
    )

    // Create DataFrame from sample data
    val df = data.toDF("id", "struct_array")

    // Define the UDF
    val countNullsUDF = udf((arr: Seq[Row]) => countNulls(arr.map(r => MyStruct(r.getString(0), r.getInt(1)))))

    // Apply the UDF to the DataFrame
    val result = df.withColumn("null_count", countNullsUDF($"struct_array"))

    result.show()
  }
}

// Define a UDF to drop duplicates in sequence
    val dropDuplicatesUDF = udf((rows: Seq[Row]) => {
      rows.foldLeft(List.empty[Row]) { (acc, row) =>
        if (acc.isEmpty || acc.head != row) row :: acc
        else acc
      }.reverse
    })



// Import required libraries
import org.apache.spark.sql.Row
import org.apache.spark.sql.functions.udf

// Define your UDF function
val iterateOverArrayUDF = udf((arrayOfArrays: Seq[Seq[Row]]) => {
  // Iterate over the outer array
  for (arr <- arrayOfArrays) {
    // Iterate over the inner array
    for (row <- arr) {
      // Convert the Row to an array of Any and iterate over it
      val rowArray = row.toSeq.toArray
      for (element <- rowArray) {
        println(element) // Or do whatever operation you need here
      }
    }
  }
})

// Example array of arrays
val arrayOfArrays: Seq[Seq[Row]] = Seq(
  Seq(Row(1, "A"), Row(2, "B"), Row(3, "C")),
  Seq(Row(4, "D"), Row(5, "E"), Row(6, "F")),
  Seq(Row(7, "G"), Row(8, "H"), Row(9, "I"))
)

// Apply the UDF to your DataFrame or Dataset
val result = someDataFrame.withColumn("resultColumn", iterateOverArrayUDF(arrayOfArrays))

// Show the result
result.show()
