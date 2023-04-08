INSERT INTO USERR (id, username, password, name, surname, active)
VALUES
    (1, 'ekrembulbul', '$2a$10$urprSaY9lvo3dIur5SJJhe7uHHsc.Bhspy63cvRMdAc20sIl7JTue', 'Ekrem', 'BÜLBÜL', true),
    (2, 'veyselbulbul', '$2a$10$aD5zzoiXlAk6nGeXIIBwH.BHHwJoGrHL9GWm891osgSYLn3ZFCZIG', 'Veysel', 'BÜLBÜL', true);

INSERT INTO ROLE (id, userr_id, role)
VALUES
    (1, 1, 'ROLE_DEV'),
    (2, 1, 'ROLE_ADMIN'),
    (3, 2, 'ROLE_ADMIN');