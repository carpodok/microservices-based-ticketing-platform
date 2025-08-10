-- hall_id 1 (Istanbul)
INSERT INTO event (hall_id, title, description, start_at, end_at, status)
VALUES
  (1, 'Symphony Night', 'Istanbul Philharmonic',
     '2025-09-01T20:00:00+03:00', '2025-09-01T22:00:00+03:00', 'PUBLISHED');

-- hall_id 2 (Ankara)
INSERT INTO event (hall_id, title, description, start_at, end_at, status)
VALUES
  (2, 'Basketball Final', 'League Championship',
     '2025-09-05T19:00:00+03:00', '2025-09-05T21:00:00+03:00', 'PUBLISHED');
