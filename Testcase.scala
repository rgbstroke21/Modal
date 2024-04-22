import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfterAll, FunSuite}

class ParquetToSequenceFileTest extends FunSuite with BeforeAndAfterAll {
  
  @transient private var spark: SparkSession = _
  
  override def beforeAll(): Unit = {
    val conf = new SparkConf().setAppName("ParquetToSequenceFileTest").setMaster("local[2]")
    spark = SparkSession.builder().config(conf).getOrCreate()
  }
  
  override def afterAll(): Unit = {
    if (spark != null) {
      spark.stop()
    }
  }

  test("Parquet to SequenceFile Conversion Test") {
    val inputPath = "path/to/input/parquet"
    val outputPath = "path/to/output/sequence"
    
    val inputDF = spark.read.parquet(inputPath)
    
    inputDF.rdd.map(row => (row.getAs[String]("key"), row.getAs[String]("value")))
              .saveAsSequenceFile(outputPath)
    
    val expectedRDD = inputDF.rdd.map(row => (row.getAs[String]("key"), row.getAs[String]("value")))
    val actualRDD = spark.sparkContext.sequenceFile[String, String](outputPath)
    
    assert(expectedRDD.collect() === actualRDD.collect(), "RDDs should match")
  }
}
