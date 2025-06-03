// import org.apache.hadoop.conf.Configuration;
// import org.apache.hadoop.fs.Path;
// import org.apache.hadoop.io.DoubleWritable; // Add this import
// import org.apache.hadoop.io.Text;
// import org.apache.hadoop.mapreduce.Job;
// import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
// import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// public class CovidCasesDriver {
//     public static void main(String[] args) throws Exception {
//         Configuration conf = new Configuration();
//         Job job = Job.getInstance(conf, "County Case Count");
        
//         job.setJarByClass(CovidCasesDriver.class);
//         job.setMapperClass(CovidCasesMapper.class);
//         job.setReducerClass(CovidCasesReducer.class);
        
//         // Set output types
//         job.setOutputKeyClass(Text.class);
//         job.setOutputValueClass(DoubleWritable.class); // Matches mapper/reducer
        
//         // Input/output paths
//         FileInputFormat.addInputPath(job, new Path(args[0]));
//         FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
//         System.exit(job.waitForCompletion(true) ? 0 : 1);
//     }
// }

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CovidCasesDriver {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "County Case Count");
        
        job.setJarByClass(CovidCasesDriver.class);
        job.setMapperClass(CovidCasesMapper.class);
        job.setReducerClass(CovidCasesReducer.class);
        
        // Set output types - must match mapper and reducer
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        
        // Input/output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}