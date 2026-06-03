INSERT INTO vehicle_types (
    id, version,
    created_at, created_by,
    updated_at, updated_by,
    deleted, deleted_at, deleted_by,
    code, name,
    active, description
)
VALUES

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'PICKUP', 'Pickup', true,
 'Small utility vehicle used for light moves and local deliveries'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'VAN', 'Van', true,
 'Medium-sized vehicle used for household relocation and small cargo'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'MINI_TRUCK', 'Mini Truck', true,
 'Light commercial truck used for urban and short-distance transport'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'TRUCK', 'Truck', true,
 'Standard heavy vehicle used for medium to large relocations'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'HEAVY_TRUCK', 'Heavy Truck', true,
 'Large capacity truck used for long-distance and bulk transport'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'TRAILER', 'Trailer', true,
 'High-capacity trailer used for industrial or large-scale logistics'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'CONTAINER_TRUCK', 'Container Truck', true,
 'Specialized truck used for container transport and shipping logistics'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'REFRIGERATED_VEHICLE', 'Refrigerated Vehicle', true,
 'Temperature-controlled vehicle used for sensitive goods and food transport');












INSERT INTO document_types (
    id, version, created_at, created_by, updated_at, updated_by, deleted,
    code, name, active, mandatory, expiry_required, description
)
VALUES
-- =========================
-- CORPORATE & LEGAL DOCUMENTS
-- =========================
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'CERTIFICATE_OF_INCORPORATION', 'Certificate of Incorporation', true, true, false, 'Official legal document confirming company registration'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'MEMORANDUM_OF_ASSOCIATION', 'Memorandum of Association (MoA)', true, true, false, 'Legal document establishing the company structure'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'ARTICLES_OF_ASSOCIATION', 'Articles of Association (AoA)', true, true, false, 'Rules governing internal management of the company'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'TRADE_LICENSE', 'Trade License', true, true, true, 'Government permission to conduct specific business activities'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'TAX_IDENTIFICATION_CERT', 'Tax Identification Certificate', true, true, false, 'Official TIN/VAT registration document'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'NON_DISCLOSURE_AGREEMENT', 'Non-Disclosure Agreement (NDA)', true, false, false, 'Confidentiality agreement with partners or clients'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'BOARD_RESOLUTION', 'Board Resolution', true, false, false, 'Formal record of decisions made by the Board of Directors'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'POWER_OF_ATTORNEY', 'Power of Attorney', true, false, true, 'Legal authorization to act on behalf of the company'),
-- =========================
-- HUMAN RESOURCES & EMPLOYEE DOCUMENTS
-- =========================
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'EMPLOYEE_ID_CARD', 'Employee ID Card', true, true, false, 'Internal company identification card'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'OFFER_LETTER', 'Offer Letter', true, false, false, 'Initial employment offer letter'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'EMPLOYMENT_AGREEMENT', 'Employment Agreement', true, true, false, 'Formal employment contract detailing terms and conditions'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'EMPLOYEE_HANDBOOK_ACK', 'Employee Handbook Acknowledgement', true, false, false, 'Signed receipt of company policies and rules'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'PAYSLIP', 'Payroll Slip / Payslip', true, false, false, 'Monthly record of employee salary and deductions'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'EMPLOYEE_TAX_DECLARATION', 'Tax Declaration Form', true, false, false, 'Employee income tax and investment declarations'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'PERFORMANCE_APPRAISAL', 'Performance Appraisal Report', true, false, false, 'Periodic employee performance review'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'DISCIPLINARY_ACTION_REPORT', 'Disciplinary Action Report', true, false, false, 'Record of warnings or disciplinary actions taken'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'RESIGNATION_LETTER', 'Resignation Letter', true, false, false, 'Formal notice of employee resignation'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'EXPERIENCE_CERTIFICATE', 'Experience/Relieving Certificate', true, false, false, 'Certificate issued to employee upon leaving the company'),
-- =========================
-- FINANCE & ACCOUNTING DOCUMENTS
-- =========================
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'PURCHASE_ORDER', 'Purchase Order (PO)', true, false, false, 'Official order issued to a vendor for goods or services'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'SALES_INVOICE', 'Sales Invoice', true, false, false, 'Invoice issued to clients for services rendered'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'VENDOR_CONTRACT', 'Vendor Contract', true, false, true, 'Agreement between the company and suppliers/subcontractors'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'BANK_STATEMENT', 'Bank Statement', true, false, false, 'Official summary of financial transactions'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'AUDIT_REPORT', 'Audit Report', true, false, false, 'Internal or external financial audit summary'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'TAX_RETURN', 'Tax Return', true, false, false, 'Annual or quarterly tax filing document'),
-- =========================
-- OPERATIONS & ADMINISTRATION DOCUMENTS
-- =========================
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'OFFICE_LEASE_AGREEMENT', 'Office Lease Agreement', true, false, true, 'Rental agreement for corporate or branch offices'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'UTILITY_BILL', 'Utility Bill', true, false, false, 'Electricity, water, or internet bills for operations'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'ASSET_HANDOVER_FORM', 'Asset Handover Form', true, false, false, 'Record of physical assets (laptops, phones) given to staff'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'VISITOR_LOG', 'Visitor Log', true, false, false, 'Record of external visitors to the premises'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'PROPERTY_INSURANCE', 'Property/Office Insurance', true, false, true, 'Insurance covering company premises and assets'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'SOFTWARE_LICENSE', 'Software License Agreement', true, false, true, 'Licensing agreement for IT infrastructure and tools'),
-- =========================
-- VEHICLE LEGAL DOCUMENTS
-- =========================
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'VEHICLE_REGISTRATION', 'Vehicle Registration', true, true, true, 'Official government registration document of vehicle'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'VEHICLE_INSURANCE', 'Vehicle Insurance', true, true, true, 'Insurance policy covering vehicle damage and liability'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'VEHICLE_FITNESS_CERTIFICATE', 'Fitness Certificate', true, true, true, 'Roadworthiness and safety compliance certificate'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'ROAD_PERMIT', 'Road Permit', true, false, true, 'Permit to operate vehicle on public roads and inter-state travel'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'VEHICLE_TAX_TOKEN', 'Tax Token', true, false, true, 'Proof of vehicle tax payment'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'EMISSION_CERTIFICATE', 'Emission/PUC Certificate', true, false, true, 'Environmental emission compliance certificate'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'CHASSIS_VERIFICATION', 'Chassis Verification', true, false, false, 'Verification of vehicle chassis authenticity'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'VEHICLE_TITLE_DEED', 'Vehicle Title Deed', true, false, false, 'Official proof of ownership for the vehicle'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'VEHICLE_LEASE_AGREEMENT', 'Vehicle Lease Agreement', true, false, true, 'Contract for leased vehicles indicating terms and validity'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'CUSTOMS_CLEARANCE', 'Customs Clearance', true, false, true, 'Required clearance documentation for cross-border transit'),
-- =========================
-- DRIVER DOCUMENTS
-- =========================
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'DRIVER_LICENSE', 'Driver License', true, true, true, 'Valid commercial driving license issued by authority'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'DRIVER_ID_CARD', 'Driver ID Card', true, true, false, 'Government issued national identification card or passport'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'DRIVER_MEDICAL_CERTIFICATE', 'Medical Certificate', true, false, true, 'Medical and vision fitness certificate for driver'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'DRIVER_POLICE_VERIFICATION', 'Police Verification', true, false, true, 'Background check and clearance from law enforcement'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'DRIVER_TRAINING_CERTIFICATE', 'Training Certificate', true, false, false, 'Professional driving and safety training certification'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'DRIVER_DRUG_TEST', 'Drug & Alcohol Test Report', true, false, true, 'Mandatory periodic substance abuse testing results'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'DRIVER_HAZMAT_ENDORSEMENT', 'HAZMAT Endorsement', true, false, true, 'Special certification for transporting hazardous materials'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'DRIVER_EMPLOYMENT_CONTRACT', 'Driver Employment Contract', true, false, false, 'Signed employment agreement with the logistics company'),
-- =========================
-- VEHICLE MAINTENANCE DOCUMENTS
-- =========================
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'VEHICLE_INSPECTION_REPORT', 'Inspection Report', true, false, true, 'Periodic DOT or internal vehicle inspection report'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'MAINTENANCE_INVOICE', 'Maintenance Invoice', true, false, false, 'Invoice for vehicle repair or routine service'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'BREAKDOWN_REPORT', 'Breakdown Report', true, false, false, 'Incident report detailing vehicle breakdown events'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'PREVENTATIVE_MAINTENANCE_LOG', 'Preventative Maint. Log', true, false, false, 'Log sheet of routine oil changes, tire rotations, etc.'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'PARTS_WARRANTY_CERT', 'Parts Warranty Certificate', true, false, true, 'Warranty documentation for newly installed major parts'),
-- =========================
-- LOGISTICS FINANCIAL & TRIP DOCUMENTS
-- =========================
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'FUEL_INVOICE', 'Fuel Invoice', true, false, false, 'Receipt of fuel purchase during a trip'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'TOLL_RECEIPT', 'Toll Receipt', true, false, false, 'Road, bridge, or tunnel toll payment receipt'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'PARKING_RECEIPT', 'Parking Receipt', true, false, false, 'Parking fee receipt for overnight or secure parking'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'WEIGH_STATION_RECEIPT', 'Weigh Station Ticket', true, false, false, 'Certified scale ticket proving legal axle weights'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'TRIP_EXPENSE_CLAIM', 'Trip Expense Claim Form', true, false, false, 'Reimbursement claim form for out-of-pocket trip expenses'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'FINE_PENALTY_RECEIPT', 'Fine/Penalty Receipt', true, false, false, 'Receipt for traffic violations or compliance fines paid'),
-- =========================
-- MOVING / RELOCATION DOCUMENTS
-- =========================
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'MOVE_JOB_CONTRACT', 'Move Job Contract', true, true, false, 'Contract between company and customer for relocation'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'GOODS_MANIFEST', 'Goods Manifest / Inventory', true, true, false, 'Detailed list of items, boxes, and furniture being moved'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'BILL_OF_LADING', 'Bill of Lading (BOL)', true, true, false, 'Legally binding document detailing the type, quantity, and destination of goods'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'DELIVERY_CONFIRMATION', 'Proof of Delivery (POD)', true, true, false, 'Signed proof of successful delivery by the customer'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'DAMAGED_ITEM_REPORT', 'Damage/Loss Report', true, false, false, 'Report of damaged or missing items during relocation'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'VALUABLES_DECLARATION', 'High-Value Declaration', true, false, false, 'Special inventory form declaring high-value items for insurance'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
 'CUSTOMER_FEEDBACK_FORM', 'Customer Feedback Form', true, false, false, 'Post-move survey and quality of service sign-off');










INSERT INTO vehicle_makes (
    id, version,
    created_at, created_by,
    updated_at, updated_by,
    deleted, deleted_at, deleted_by,
    code, name,
    active, country
)
VALUES

-- =========================
-- JAPAN (Most used in logistics)
-- =========================

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'TOYOTA', 'Toyota', true, 'Japan'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'HINO', 'Hino', true, 'Japan'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'ISUZU', 'Isuzu', true, 'Japan'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'MITSUBISHI_FUSO', 'Mitsubishi Fuso', true, 'Japan'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'NISSAN_DIESEL', 'Nissan Diesel', true, 'Japan'),

-- =========================
-- EUROPE
-- =========================

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'MERCEDES_BENZ', 'Mercedes-Benz', true, 'Germany'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'VOLVO', 'Volvo Trucks', true, 'Sweden'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'SCANIA', 'Scania', true, 'Sweden'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'MAN', 'MAN Truck & Bus', true, 'Germany'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'IVECO', 'Iveco', true, 'Italy'),

-- =========================
-- USA
-- =========================

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'FORD', 'Ford', true, 'USA'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'CHEVROLET', 'Chevrolet', true, 'USA'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'FREIGHTLINER', 'Freightliner', true, 'USA'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'PETERBILT', 'Peterbilt', true, 'USA'),

-- =========================
-- CHINA (emerging logistics fleets)
-- =========================

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'FOTON', 'Foton', true, 'China'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'FAW', 'FAW Group', true, 'China'),

(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'DONGFENG', 'Dongfeng', true, 'China');
