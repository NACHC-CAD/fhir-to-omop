package org.nachc.tools.fhirtoomop.omop.write.threaded;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.file.FhirPatientResourcesAsFilesFactory;
import org.nachc.tools.fhirtoomop.omop.write.threaded.runnable.WriteOmopPeopleToDatabaseRunnable;
import org.nachc.tools.threadtool.SimpleThreadRunner;
import org.nachc.tools.threadtool.ThreadRunner;
import org.nachc.tools.threadtool.runnableiter.ThreadToolUser;
import org.nachc.tools.threadtool.worker.ThreadToolWorker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteOmopPeopleToDatabase implements ThreadToolUser {

	private List<String> fileList;

	private List<Connection> connList;

	private int numberOfWorkers;

	private int numberOfPatientsPerWorker;
	
	private int numberOfThreadsPerWorker;
	
	private Object lock = new Object();

	public WriteOmopPeopleToDatabase(List<String> fileList, List<Connection> connList, int numberOfWorkers, int numberOfPatientsPerWorker, int numberOfThreadsPerWorker) {
		this.fileList = fileList;
		this.connList = connList;
		this.numberOfWorkers = numberOfWorkers;
		this.numberOfPatientsPerWorker = numberOfPatientsPerWorker;
		this.numberOfThreadsPerWorker = numberOfThreadsPerWorker;
	}

	public void exec() {
		log.info("-----------------------------------------");
		log.info("STARTING NEW WRITER JOB");
		log.info("NUMBER OF PATIENTS: " + this.fileList.size());
		log.info("-----------------------------------------");
		ThreadRunner runner = new ThreadRunner(numberOfThreadsPerWorker, numberOfPatientsPerWorker, numberOfWorkers, this);
		runner.exec();
	}

	private Connection getConn() {
		int index = this.fileList.size() % this.connList.size();
		return this.connList.get(index);
	}
	
	@Override
	public Runnable getNext() {
		synchronized (lock) {
			String dirName = fileList.remove(0);
			FhirPatientResources resources =  FhirPatientResourcesAsFilesFactory.getForPatient(dirName);
			Connection conn = getConn();
			WriteOmopPeopleToDatabaseRunnable runnable = new WriteOmopPeopleToDatabaseRunnable(resources, conn);
			return runnable;
		}
	}

	@Override
	public boolean hasNext() {
		synchronized (lock) {
			if(this.fileList.size() == 0) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public int waiting() {
		return this.fileList.size();
	}

}
