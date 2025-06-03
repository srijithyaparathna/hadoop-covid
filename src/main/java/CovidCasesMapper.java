import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CovidCasesMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    private Text county = new Text();
    private LongWritable cases = new LongWritable();

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        // Skip header line
        if (key.get() == 0) return;

        String[] fields = value.toString().split(",", -1);

        // Check for sufficient columns and non-empty county name
        if (fields.length > 10 && !fields[6].trim().isEmpty()) {
            String countyName = fields[6].trim(); // provience column
            String caseCountStr = fields[10].trim(); // Confirmed column

            try {
                long caseCount = Long.parseLong(caseCountStr);
                // Include ALL cases, even 0
                county.set(countyName);
                cases.set(caseCount);
                context.write(county, cases);
            } catch (NumberFormatException e) {
                // Skip only if not a valid number
            }
        }
    }
}