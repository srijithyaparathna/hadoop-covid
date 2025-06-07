# COVID-19 County Case Count (Hadoop MapReduce)

A Java MapReduce project to process COVID-19 county-level case data using Apache Hadoop.

This project is designed for a hybrid setup where Hadoop runs in WSL (Windows Subsystem for Linux), Java code is developed on Windows, and jobs are submitted using Windows-based Hadoop scripts.

---

##  Prerequisites

- Hadoop 3.x installed and configured (Tested on Hadoop 3.x)
- Java JDK 8+
- Maven (for building the JAR)
- Windows Subsystem for Linux (WSL) enabled
- HDFS and YARN configured and running

---

##  Project Structure

```
hadoop-covid/
├── src/
│   └── main/
│       ├── java/
│       │   ├── CovidCasesDriver.java
│       │   ├── CovidCasesMapper.java
│       │   └── CovidCasesReducer.java
│       └── resources/
│           └── log4j.properties
├── pom.xml
├── usa_county_wise.csv
└── target/
    └── hadoop-covid-1.0-SNAPSHOT.jar
```

---

##  Setup & Configuration

### 1. Hadoop on WSL

- Install WSL and a Linux distribution (e.g., Ubuntu)
- Install Hadoop and configure `core-site.xml`, `hdfs-site.xml`, `yarn-site.xml`, `mapred-site.xml`
- Format the Hadoop NameNode:

```bash
hdfs namenode -format
```

### 2. Java Project on Windows

- Develop Java code using an IDE like VS Code
- Build the project with Maven:

```bash
mvn clean package
```

- Output JAR: `target/hadoop-covid-1.0-SNAPSHOT.jar`

### 3. Start Hadoop Services (on Windows)

Navigate to the Hadoop `bin` directory in Command Prompt:

```cmd
cd E:\hadoop\hadoop\hadoop\bin
```

Start HDFS and YARN:

```cmd
start-dfs.cmd
start-yarn.cmd
```

---

##  Usage

### 1. Upload Data to HDFS

```bash
hdfs dfs -mkdir -p /cse532/input
hdfs dfs -put usa_county_wise.csv /cse532/input/
```

### 2. Run the MapReduce Job

```bash
yarn jar target/hadoop-covid-1.0-SNAPSHOT.jar CovidCasesDriver /cse532/input/usa_county_wise.csv /cse532/output
```

**Note:** Delete output directory if it already exists:

```bash
hdfs dfs -rm -r /cse532/output
```

### 3. Monitor Job Progress

- HDFS UI: [http://localhost:9870](http://localhost:9870)
- YARN ResourceManager UI: [http://localhost:8088](http://localhost:8088)
- Check running daemons:

```bash
jps
```

### 4. View Output

```bash
hdfs dfs -ls /cse532/output
hdfs dfs -cat /cse532/output/part-r-00000
```

---

##  Stopping Hadoop

```cmd
stop-yarn.cmd
stop-dfs.cmd
```

---

##  Troubleshooting

- **Output directory exists:**  
  Run `hdfs dfs -rm -r /cse532/output` to remove it.

- **No output or job fails:**  
  Use YARN UI to view logs and error messages.

- **Updating Java code:**  
  Rebuild the project using `mvn clean package`.

---

##  References

- [Hadoop Cluster Setup (Apache Docs)](https://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-common/ClusterSetup.html)



