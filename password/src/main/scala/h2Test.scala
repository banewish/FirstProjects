import java.sql.Connection
import java.sql.DriverManager

object TestH2 {

  private def createDbStructure(conn: Connection): Unit = {

    val sql = """
      create schema if not exists dbhmon;

      set schema dbhmon;

      create table if not exists check_count (
        id int auto_increment primary key,
        db_url varchar(255) not null,
        table_name varchar(255) not null,
        cnt bigint,
        start_dtm timestamp not null,
        end_dtm timestamp,
        err_msg varchar,
        err_stacktrace varchar);"""
    val stmt = conn.createStatement()
    try {
      stmt.execute(sql)
    }
    finally {
      stmt.close
    }

  }

  def main(args: Array[String]): Unit = {
    Class.forName("org.postgresql.Driver")
    val conn: Connection = DriverManager.getConnection( "jdbc:postgresql://host:8081/passwordTable", "postgres", "1234" )
    try {
      createDbStructure(conn)
      println( "ok")
    }
    finally {
      conn.close()
    }
  }
}

