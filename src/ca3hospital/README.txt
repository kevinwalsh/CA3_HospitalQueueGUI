
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* 
* ASSESSMENT 3:		Hospital A&E Queue Prioritization GUI
* 
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *


FUNCTIONAL OBJECTIVE:
- Build an application to manage the overall A&E treatment procedure, particularly queue management & prioritization.
- 3 stages of system: Receptionist resigtration, Nurse assessment, Doctor Treatment
- (1) Patients present themselves to receptionist, and are entered into system first-come-first-served.
		They also give generic details (name/address/etc) plus symptoms, which are entered into a form.
- (2) When nurse is ready, they pull details for next patient from the queue, and call them from watiting room.
		Following nurse's assessment, a priority level is assigned to patient (1-10). This governs queue to see doctor.
		(Meanwhile, patient returns to waiting room).
- (3) When doctor is ready, they pull details for next patient (ordered first by priority, and THEN by longest waiting),
		then treat the patient, enter prescribed treatment, and send patient home & remove them from system.


---------------------------------------
TECHNICAL OBJECTIVES:
- Demonstrate technical flexibility by implementing queueing system using Doubly-Linked Lists written from first principles,
	instead of a default List.
- Adhere to SOLID principles as closely as possible.
- Construct a GUI system to display relevant information & data entry in a window.
- Allow multiple users at once are able to use system concurrently
- Ensure queueing protocols behave as expected as per functional requirements
- Provide data persistence, in case of required restart during business operation.

---------------------------------------
DESIGN FEATURES
- Demonstrate use of S.O.L.I.D
- implement a GUI for more user-friendly input & output
- data sorting & prioritization based on different algorithms for different stages
- multiple-user concurrent access
- Data persistence and database utilization/interaction
- Bug catching and system testing
	- higher-priority patient is called by doctor before an earlier-but-lower-priority patient
	- can have multiple screens open at once (to represent a multiple-user system, though simulated on a single machine)
	- "no Duplicate responsibilities" -multiple operators calling patients from queue will not pull duplicate records for same patient
	- empty queues/ end-of-line displays an info message to user instead of causing any error
	- nurse cannot enter priority outside 0-10 range
	- doctor cannot call patients awaiting nurse
	- "live updating" - if nurse finishes with patient while doctor has window open, doctor does NOT need to close & refresh
	- records lock & release - First, if one operator is with a patient, this record is temporarily removed from queue
		so nobody else can request this patient.
		More importantly, if this operator closes window or anything happens, patient will be re-entered into queue to be seen
		by next available operator.
