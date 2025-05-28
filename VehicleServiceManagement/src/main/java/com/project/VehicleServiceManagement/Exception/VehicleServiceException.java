package com.project.VehicleServiceManagement.Exception;

public class VehicleServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VehicleServiceException(String message) {
		super(message);
	}

	public VehicleServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

class ServiceAdvisorNotFoundException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public ServiceAdvisorNotFoundException(Long advisorId) {
		super("Service Advisor with ID " + advisorId + " not found");
	}

	public ServiceAdvisorNotFoundException(String message) {
		super(message);
	}
}

class ServiceAdvisorNotAvailableException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public ServiceAdvisorNotAvailableException(Long advisorId) {
		super("Service Advisor with ID " + advisorId + " is not available (already occupied)");
	}

	public ServiceAdvisorNotAvailableException(String message) {
		super(message);
	}
}

class VehicleServiceNotFoundException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public VehicleServiceNotFoundException(Long serviceId) {
		super("Vehicle service with ID " + serviceId + " not found");
	}

	public VehicleServiceNotFoundException(String message) {
		super(message);
	}
}

class InvalidServiceStatusException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public InvalidServiceStatusException(String message) {
		super(message);
	}
}

class ServiceNotDueException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public ServiceNotDueException(Long serviceId) {
		super("Service with ID " + serviceId + " is not in DUE status and cannot be assigned");
	}

	public ServiceNotDueException(String message) {
		super(message);
	}
}

class ProvidedServiceNotFoundException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public ProvidedServiceNotFoundException(Long serviceId) {
		super("Provided service with ID " + serviceId + " not found");
	}

	public ProvidedServiceNotFoundException(String message) {
		super(message);
	}
}

class InsufficientQuantityException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public InsufficientQuantityException(String serviceName, int available, int requested) {
		super("Insufficient quantity for service '" + serviceName + "'. Available: " + available + ", Requested: "
				+ requested);
	}

	public InsufficientQuantityException(String message) {
		super(message);
	}
}

// SERVICE RECORD RELATED EXCEPTIONS 
class ServiceRecordNotFoundException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public ServiceRecordNotFoundException(Long recordId) {
		super("Service record with ID " + recordId + " not found");
	}

	public ServiceRecordNotFoundException(String message) {
		super(message);
	}
}

class ServiceAlreadyCompletedException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public ServiceAlreadyCompletedException(Long serviceId) {
		super("Service with ID " + serviceId + " is already completed");
	}

	public ServiceAlreadyCompletedException(String message) {
		super(message);
	}
}

class UserNotFoundException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String email) {
		super("User with email " + email + " not found");
	}

}

class InvalidCredentialsException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException() {
		super("Invalid email or password provided");
	}

	public InvalidCredentialsException(String message) {
		super(message);
	}
}

class UserAlreadyExistsException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}

// BUSINESS LOGIC RELATED EXCEPTIONS 

class InvalidDateRangeException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public InvalidDateRangeException() {
		super("Invalid date range: start date must be before end date");
	}

	public InvalidDateRangeException(String message) {
		super(message);
	}
}

class EmptyBillException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public EmptyBillException() {
		super("Cannot create bill with no items");
	}

	public EmptyBillException(String message) {
		super(message);
	}
}

class InvalidQuantityException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public InvalidQuantityException() {
		super("Quantity must be greater than zero");
	}

	public InvalidQuantityException(String message) {
		super(message);
	}
}

// ADDITIONAL USEFUL EXCEPTIONS FOR YOUR PROJECT  

class DuplicateVehicleException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public DuplicateVehicleException(String vehicleNo) {
		super("Vehicle with number " + vehicleNo + " already exists in the system");
	}
}

class InvalidVehicleNumberException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public InvalidVehicleNumberException(String vehicleNo) {
		super("Invalid vehicle number format: " + vehicleNo);
	}
}

class ServiceNotOngoingException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public ServiceNotOngoingException(Long serviceId) {
		super("Service with ID " + serviceId + " is not in ONGOING status");
	}
}

class BillNotFoundException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public BillNotFoundException(Long billId) {
		super("Bill with ID " + billId + " not found");
	}
}

class InvalidPriceException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public InvalidPriceException() {
		super("Price must be greater than zero");
	}
}

class InvalidDaysToCompleteException extends VehicleServiceException {

	private static final long serialVersionUID = 1L;

	public InvalidDaysToCompleteException() {
		super("Days to complete must be greater than zero");
	}
}

class ServiceAdvisorOccupiedException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public ServiceAdvisorOccupiedException(Long advisorId) {
		super("Service Advisor with ID " + advisorId + " is currently occupied with another service");
	}
}

class InvalidServiceAssignmentException extends VehicleServiceException {

	private static final long serialVersionUID = 1L;

	public InvalidServiceAssignmentException(String message) {
		super(message);
	}
}

class QuantityUpdateException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public QuantityUpdateException(String serviceName) {
		super("Failed to update quantity for service: " + serviceName);
	}
}

class InvalidDateException extends VehicleServiceException {
	private static final long serialVersionUID = 1L;

	public InvalidDateException(String message) {
		super("Invalid date: " + message);
	}
}
