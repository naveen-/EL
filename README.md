# EL
Oozie EL Function

	Initial setup:
	Java jar: EL-0.1.jar 
	Copy to location: /var/lib/oozie
	Update Oozie-size.xml with below property
		<property>
	   		 <name>oozie.service.ELService.ext.functions.workflow</name>
	   		 <value>
	 		        getDates=com.vfq.prism.EL.PrismELFunctions#getDates
	 		   </value>
	</property>
			Restart oozie server.
	
	Java class: PrismELFunctions
	Java function: getDates
	    This function returns list of dates  in yyyyMMdd,yyyyMMdd,yyyyMMdd format.
	    Inputs current_date in yyyyMMdd format and number_of_days.
	    Number of days can be –ve or +ve.
	    Example:
	    getDates(‘20150902’,-5) will return  ‘20150902,20150901,20150831,20150830,20150829’
	    getDates(‘20150912’,5) will return ‘20150912,20150913,20150914,20150915,20150916’

	Example usage:
	coordinator.xml
	  <property>
	       <name>date_yyyyMMdd</name>
	       <value>${coord:formatTime(coord:nominalTime(),&#39;yyyyMMdd$#39;)}</value>
	  </property>
	  
	job.properties
	    inputPath=/source/input/
	    dt=${date_yyyyMMdd}
      
	workflow.xml
	   <workflow-app name="TestEL" xmlns="uri:oozie:workflow:0.4">
	      <parameters>
	        <property>
	          <name>currentDt</name>
	          <value>${dt}</value>
	        </property>
	      </parameters>
      
      ..
      ..
      In some actoin add below function
      <param>inputParam=${inputPath}{${getDates(currentDt,-3)}}/*</param>
      
