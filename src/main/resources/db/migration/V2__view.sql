CREATE OR REPLACE JSON RELATIONAL DUALITY VIEW "STUDENT_SCHEDULE" AS
SELECT JSON{
        'studentId': s."ID",
        'student': s."NAME" WITH UPDATE,
        'averageGrade': s."AVERAGE_GRADE" WITH UPDATE,
        'schedule': [SELECT JSON{'id': sc."ID",
                                 'class': (SELECT JSON{'classID': c."ID",
                                                       'teacher': (SELECT JSON{'teachID': t."ID",
                                                                                'teacher': t."NAME"}
                                                                    FROM "TEACHER" t WITH UPDATE WHERE c."TEACHER_ID" = t."ID"),
                                                       'room': c."ROOM",
                                                       'time': c."TIME",
                                                       'name': c."NAME" WITH UPDATE}
                                           FROM "CLASS" c WITH UPDATE WHERE sc."CLASS_ID" = c."ID")}
                      FROM "STUDENT_CLASSES" sc WITH INSERT UPDATE DELETE WHERE s."ID" = sc."STUDENT_ID"]}
FROM "STUDENT" s WITH UPDATE INSERT DELETE;