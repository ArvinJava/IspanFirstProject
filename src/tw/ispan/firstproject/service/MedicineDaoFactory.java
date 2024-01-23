package tw.ispan.firstproject.service;


public class MedicineDaoFactory {
	
	public static IMedicineDao createMedicineDao() {
		return new MedicineImpl();
		
	}

}
