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
