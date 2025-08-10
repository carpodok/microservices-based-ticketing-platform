
-- event_id 1 (Symphony Night)
INSERT INTO price_tier (event_id, name, price, currency)
VALUES
  (1, 'VIP',     750.00, 'TRY'),
  (1, 'Regular', 250.00, 'TRY');

-- event_id 2 (Basketball Final)
INSERT INTO price_tier (event_id, name, price, currency)
VALUES
  (2, 'Courtside', 900.00, 'TRY'),
  (2, 'Standard',  300.00, 'TRY');
