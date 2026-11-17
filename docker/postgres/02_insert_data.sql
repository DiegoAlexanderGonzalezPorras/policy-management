INSERT INTO public.policies (
    policy_number,
    policy_type,
    status,
    start_date,
    policy_duration,
    monthly_rent,
    premium_amount,
    auto_renewal
)
VALUES
(
    'POL-2026-0001',
    'INDIVIDUAL',
    'ACTIVA',
    '2026-01-01',
    12,
    1500000.00,
    18000000.00,
    TRUE
),
(
    'POL-2026-0002',
    'COLECTIVA',
    'ACTIVA',
    '2026-02-01',
    12,
    2500000.00,
    30000000.00,
    TRUE
);

INSERT INTO risks (description) VALUES
('Incendio'),
('Terremoto'),
('Inundación'),
('Robo y hurto');

INSERT INTO policy_risks (policy_number, id_risk) VALUES
('POL-2026-0001', 1),
('POL-2026-0002', 2),
('POL-2026-0002', 3),
('POL-2026-0002', 4);