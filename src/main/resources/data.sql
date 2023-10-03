CREATE TABLE IF NOT EXISTS accounts
(
    id              bigserial           PRIMARY KEY                 NOT NULL,
    number          numeric(18)         UNIQUE                      NOT NULL,
    balance         numeric(12,2)                                   NOT NULL
);

CREATE INDEX IF NOT EXISTS accounts_idx_ ON accounts USING btree (number);

CREATE TABLE IF NOT EXISTS owners
(
    id              bigserial       PRIMARY KEY                     NOT NULL,
    name            text            UNIQUE                          NOT NULL,
    pin             text                                            NOT NULL,
    account_id      bigint          REFERENCES      accounts        NOT NULL
);

CREATE INDEX IF NOT EXISTS owners_idx_name ON owners USING btree (name);
CREATE INDEX IF NOT EXISTS owners_idx_pin ON owners USING btree (pin);