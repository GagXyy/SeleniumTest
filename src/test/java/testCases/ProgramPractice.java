package testCases;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;

public class ProgramPractice {


	
	    public static void main(String[] args) {
	        // Create a ScheduledExecutorService
	        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	        // Define the target time (2:39 AM today)
	        LocalDateTime now = LocalDateTime.now();
	        LocalDateTime targetTime = now.withHour(15).withMinute(17).withSecond(0).withNano(0);

	        // Check if the target time is in the past
	        if (now.isAfter(targetTime)) {
	            System.out.println("The specified time has already passed for today.");
	            return; // Exit if the time has already passed
	        }

	        // Calculate the delay in seconds
	        long delay = ChronoUnit.SECONDS.between(now, targetTime);

	        // Define the task to print a message
	        Runnable task = () -> System.out.println("Hello! This message is printed at: " + LocalDateTime.now());

	        // Schedule the task to run after the calculated delay
	        scheduler.schedule(task, delay, TimeUnit.SECONDS);

	        // Optional: Keep the main thread alive to wait for the scheduled task
	        try {
	            Thread.sleep((delay + 1) * 1000); // Sleep a bit longer than the delay to ensure task execution
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } finally {
	            scheduler.shutdown(); // Shutdown the scheduler
	        }
	    }
	}

		    
		

